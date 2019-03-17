package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.EmptyText;
import com.maviteixeira.store.shared.Text;

public class EmptyName implements Name {

    @Override
    public Text asText() {
        return new EmptyText();
    }
}
