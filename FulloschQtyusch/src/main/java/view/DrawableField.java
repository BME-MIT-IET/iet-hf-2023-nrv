package view;

import model.map.Field;

public class DrawableField extends Field  implements Drawable {
    @Override
    public String getTexture() {
        return "/textures/Field.png";
    }
}
