package com.qualizeal.community.web.constants;

import org.openqa.selenium.Dimension;

public enum ViewPort {
    IPHONE_12(new Dimension(390, 844)),
    IPHONE_12_MINI(new Dimension(360, 780)),
    IPHONE_12_PRO(new Dimension(390, 844)),
    IPHONE_12_PRO_MAX(new Dimension(428, 926)),
    IPHONE_SE(new Dimension(214, 379)),
    IPHONE_11(new Dimension(414, 896)),
    IPHONE_11_XR(new Dimension(414, 896)),
    IPHONE_11_PRO(new Dimension(375, 812)),
    IPHONE_11_XS(new Dimension(375, 812)),
    IPHONE(new Dimension(375, 812)),
    IPHONE_8_PLUS(new Dimension(414, 736)),
    IPHONE_8(new Dimension(375, 667)),
    IPHONE_7_PLUS(new Dimension(414, 736)),
    IPHONE_7(new Dimension(375, 667)),
    IPHONE_6S_PLUS(new Dimension(414, 736)),
    IPHONE_6S(new Dimension(375, 667)),
    IPHONE_6_PLUS(new Dimension(414, 736)),
    IPHONE_6(new Dimension(375, 667)),
    IPAD_PRO(new Dimension(1024, 1366)),
    IPAD_AIR(new Dimension(768, 1024)),
    IPAD_MINI(new Dimension(768, 1024)),
    NEXUS_6P(new Dimension(411, 731)),
    NEXUS_5X(new Dimension(411, 731)),
    GOOGLE_PIXEL(new Dimension(411, 731)),
    GOOGLE_PIXEL_XL(new Dimension(411, 731)),
    GOOGLE_PIXEL_2(new Dimension(411, 731)),
    GOOGLE_PIXEL_2_XL(new Dimension(411, 823)),
    LG_G5(new Dimension(360, 640)),
    LG_G4(new Dimension(360, 640)),
    LG_G3(new Dimension(360, 640)),
    ONE_PLUS_3(new Dimension(480, 853)),
    SAMSUNG_GALAXY_S9(new Dimension(360, 740)),
    SAMSUNG_GALAXY_S8(new Dimension(360, 740)),
    SAMSUNG_GALAXY_S7(new Dimension(360, 640)),
    NEXUS_7(new Dimension(600, 960)),
    NEXUS_9(new Dimension(768, 1024)),
    SAMSUNG_GALAXY_TAB(new Dimension(800, 1280)),
    CHROMEBOOK_PIXEL(new Dimension(1280, 850));

    private final Dimension dimension;

    ViewPort(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimensions() {
        return this.dimension;
    }
}
