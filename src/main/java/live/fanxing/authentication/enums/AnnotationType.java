package live.fanxing.authentication.enums;

import java.util.Locale;

public enum AnnotationType {
    TYPE(true),METHOD(false);

    boolean type;

    AnnotationType(boolean b) {
        this.type = b;
    }

    public boolean getAnnontationType() {
        return this.type;
    }
}
