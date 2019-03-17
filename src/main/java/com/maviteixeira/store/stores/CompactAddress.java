package com.maviteixeira.store.stores;

import com.maviteixeira.store.shared.NoNullText;
import com.maviteixeira.store.shared.Text;
import com.maviteixeira.store.shared.TextOf;

public class CompactAddress implements Address {

    private final Text compactAddress;

    public CompactAddress(Text compactAddress) {
        this.compactAddress = compactAddress;
    }

    public CompactAddress(String compactAddress) {
        this(new TextOf(compactAddress));
    }

    @Override
    public Text asText() {
        return new NoNullText(compactAddress);
    }
}
