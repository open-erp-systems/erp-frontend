package com.jukusoft.erp.gui.menu.impl;

import com.jukusoft.erp.gui.cache.Cache;
import com.jukusoft.erp.gui.cache.ICache;
import com.jukusoft.erp.gui.eventbus.EventBus;
import com.jukusoft.erp.gui.menu.MenuManager;
import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.message.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class DefaultMenuManager implements MenuManager {

    //menu cache
    protected ICache menuCache = null;

    public DefaultMenuManager () {
        //create new menu cache
        this.menuCache = Cache.get("menu-cache");
    }

    @Override
    public void fillMenu(MenuBar menuBar, Stage stage, int menuID) {
        //first, remove all outdated menu items
        menuBar.getMenus().clear();

        //get all menu entries for menu

        //first, lock if menu structure is in cache
        if (this.menuCache.contains("menu-entries-" + menuID)) {
            //load from cache
            parseMenu(stage, menuBar, this.menuCache.getArray("menu-entries-" + menuID));

            return;
        }

        //load menu per network operation
        Message msg = Message.createRequest("/list-menus");
        msg.getData().put("menuID", menuID);

        //send request to get menu structure
        NetworkManager.getInstance().send(msg, res -> {
            if (!res.succeeded()) {
                JsonObject json = new JsonObject();
                json.put("title", "Error");
                json.put("text", "Couldnt load menu structure, error message by network operation: " + res.causeMessage());

                //show error dialog
                EventBus.getInstance().raiseEvent("warning_notification", json);

                return;
            }

            if (!res.result().succeeded()) {
                JsonObject json = new JsonObject();
                json.put("title", "Error");
                json.put("text", "Couldnt load menu structure."/* + res.causeMessage()*/);

                //show error dialog
                EventBus.getInstance().raiseEvent("warning_notification", json);

                return;
            }

            //get json array
            JsonArray menuArray = res.result().getData().getJsonArray("menu");

            //save menu structure to cache
            this.menuCache.putArray("menu-entries-" + menuID, menuArray);

            //parse menu
            this.parseMenu(stage, menuBar, menuArray);
        });
    }

    protected void parseMenu (Stage stage, MenuBar menuBar, JsonArray jsonArray) {
        Platform.runLater(() -> {
            for (int i = 0; i < jsonArray.size(); i++) {
                //get menu entry
                JsonObject entry = jsonArray.getJsonObject(i);

                //get event and title
                //String event = entry.getString("event");
                String title = entry.getString("title");

                //create new menu
                Menu menu = new Menu(title);

                //add sub menus
                this.parseSubMenus(menu, entry.getJsonArray("submenus"));

                //add menu to menu bar
                menuBar.getMenus().add(menu);
            }

            menuBar.prefWidthProperty().bind(stage.widthProperty());
        });
    }

    protected void parseSubMenus (Menu menu, JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            //get json object
            JsonObject entry = jsonArray.getJsonObject(i);

            //get event and title
            String event = entry.getString("event");
            String title = entry.getString("title");

            //create new menu item
            MenuItem menuItem = new MenuItem(title);

            //add click handler
            menuItem.setOnAction(event1 -> {
                System.out.println("open activitiy: " + event);

                EventBus.getInstance().raiseEvent("open_activity", event);
            });

            menuItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

            //check for shortcut
            if (entry.containsKey("shortcut") && !entry.getString("shortcut").isEmpty()) {
                String keys[] = entry.getString("shortcut").split("\\+");

                if (keys.length > 1) {
                    KeyCombination.Modifier modifier = null;

                    if (keys[1].equals("SHIFT_DOWN")) {
                        modifier = KeyCombination.SHIFT_DOWN;
                    } else {
                        throw new UnsupportedOperationException("Unsupported key code: " + keys[1]);
                    }

                    //KeyCombination combination = KeyCombination.keyCombination(keys[1]);

                    menuItem.setAccelerator(new KeyCodeCombination(KeyCode.getKeyCode(keys[0]), modifier));
                } else if (keys.length == 1) {
                    menuItem.setAccelerator(new KeyCodeCombination(KeyCode.getKeyCode(keys[0])));
                } else {
                    //no shortcut is set
                }
            }

            //add menu item to menu
            menu.getItems().add(menuItem);
        }
    }

}
