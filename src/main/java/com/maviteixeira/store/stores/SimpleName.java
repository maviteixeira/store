package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.NoNullText;
import com.maviteixeira.store.shared.Text;
import com.maviteixeira.store.shared.TextOf;

public class SimpleName implements Name {

    private final Text fullName;

    public SimpleName(Text fullName) {
        this.fullName = fullName;
    }

    public SimpleName(String fullName) {
        this(new TextOf(fullName));
    }

    @Override
    public Text asText() {
        return new NoNullText(fullName);
    }
}
