package view;

import model.map.Warehouse;

public class DrawableWarehouse extends Warehouse  implements Drawable {
    @Override
    public String getTexture() {

        return "/textures/Warehouse.png";
    }
}
