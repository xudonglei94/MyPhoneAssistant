package org.crazyit.myphoneassistant.common.font;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator on 2018/6/16.
 */

public class MyPhoneFont implements ITypeface {
    private static final String TTF_FILE = "iconfont.ttf";
    private static Typeface typeface = null;
    private static HashMap<String, Character> mChars;


    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }
        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "cniao";
    }

    @Override
    public String getFontName() {
        return "cniao";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();
        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    @Override
    public String getAuthor() {
        return "www.cniao5.com";
    }

    @Override
    public String getUrl() {
        return "http://wwww.cniao5.com/";
    }

    @Override
    public String getDescription() {
        return "The premium icon font for Ionic Framework.";
    }

    @Override
    public String getLicense() {
        return "MIT Licensed";
    }

    @Override
    public String getLicenseUrl() {
        return "http://wwww.cniao5.com/";
    }

    @Override
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(),   TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }





    public enum Icon implements IIcon {



        cniao_download('\ue600'),
        cniao_head('\ue61b'),
        cniao_shutdown('\ue68e');






        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new MyPhoneFont();
            }
            return typeface;
        }
    }
}
