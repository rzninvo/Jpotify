package View;

import javax.swing.*;
import java.awt.*;

public class Icons {
    static ImageIcon JPOTIFY_ICON = new ImageIcon("src/View/Icons/JPotify.png");
    static ImageIcon VOLUME_ICON = new ImageIcon("src/View/Icons/Volume.png");
    static ImageIcon BROWSE_ICON = new ImageIcon("src/View/Icons/browse.png");
    static ImageIcon BROWSE_FOCUSED_ICON = new ImageIcon("src/View/Icons/browse_focused.png");
    static ImageIcon DOWN_ARROW_ICON = new ImageIcon("src/View/Icons/down_arrow.png");
    static ImageIcon HOME_ICON = new ImageIcon("src/View/Icons/home.png");
    static ImageIcon HOME_FOCUSED_ICON = new ImageIcon("src/View/Icons/home_focused.png");
    static ImageIcon LEFT_ARROW_ICON = new ImageIcon("src/View/Icons/left_arrow.png");
    static ImageIcon MENU_ICON = new ImageIcon("src/View/Icons/menu.png");
    static ImageIcon RADIO_ICON = new ImageIcon("src/View/Icons/radio.png");
    static ImageIcon RADIO_FOCUSED_ICON = new ImageIcon("src/View/Icons/radio_focused.png");
    static ImageIcon RIGHT_ARROW_ICON = new ImageIcon("src/View/Icons/right_arrow.png");
    static ImageIcon UP_ARROW_ICON = new ImageIcon("src/View/Icons/up_arrow.png");
    static ImageIcon USER_ICON = new ImageIcon("src/View/Icons/user.png");
    static ImageIcon EXIT_ICON = new ImageIcon("src/View/Icons/exit.png");
    static ImageIcon MINIMIZE_ICON = new ImageIcon("src/View/Icons/minimize.png");
    static ImageIcon PLAY_ICON = new ImageIcon("src/View/Icons/play.png");
    static ImageIcon PREV_SIZE_ICON = new ImageIcon("src/View/Icons/prev_size.png");
    static ImageIcon REPEAT_ICON = new ImageIcon("src/View/Icons/repeat.png");
    static ImageIcon SEARCH_ICON = new ImageIcon("src/View/Icons/search.png");
    static ImageIcon SHUFFLE_ICON = new ImageIcon("src/View/Icons/shuffle.png");
    static ImageIcon SKIP_BACKWARD_ICON = new ImageIcon("src/View/Icons/skip_backward.png");
    static ImageIcon SKIP_FORWARD_ICON = new ImageIcon("src/View/Icons/skip_forward.png");
    static ImageIcon ADD_NEW_ICON = new ImageIcon("src/View/Icons/add_new.png");
    static ImageIcon ADD_ICON = new ImageIcon("src/View/Icons/add.png");
    static ImageIcon SEARCH2_ICON = new ImageIcon("src/View/Icons/search2.png");
    static ImageIcon CLOSE2_ICON = new ImageIcon("src/View/Icons/close2.png");
    static ImageIcon USER2_ICON = new ImageIcon("src/View/Icons/user2.png");
    static ImageIcon USER3_ICON = new ImageIcon("src/View/Icons/user3.png");
    static ImageIcon USER4_ICON = new ImageIcon("src/View/Icons/user4.png");
    static ImageIcon PAUSE_ICON = new ImageIcon("src/View/Icons/pause.png");
    static ImageIcon JPOTIFYLOGIN_ICON = new ImageIcon("src/View/Icons/JPotifyLogo.png");
    static ImageIcon NO_ARTWORK = new ImageIcon("src/View/Icons/no_artwork");

    public static ImageIcon rescaleIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
