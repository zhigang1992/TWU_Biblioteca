package com.twu.biblioteca;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.twu.biblioteca.exception.AccessDeniedException;
import com.twu.biblioteca.model.Commodity;
import com.twu.biblioteca.security.UserCredentials;

import java.util.List;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.tryFind;

public class Library<T extends Commodity> {
    protected List<T> commodities;

    public boolean returned(final T commodity) throws AccessDeniedException {
        authorize();
        Optional<T> theCommodity = tryFind(commodities, new Predicate<T>() {
            @Override
            public boolean apply(T aCommodity) {
                return commodity.equals(aCommodity) && aCommodity.isCheckedout();
            }
        });

        if (theCommodity.isPresent()) {
            theCommodity.get().setCheckedout(false);
            return true;
        }

        return false;
    }

    private void authorize() throws AccessDeniedException {
        if(!UserCredentials.getInstance().success()) throw new AccessDeniedException("Permission denied, Please login as a user");
    }

    public Optional<T> checkout(final T commodity) throws AccessDeniedException {
        authorize();
        Optional<T> theCommodity = tryFind(commodities, new Predicate<T>() {
            @Override
            public boolean apply(T aCommodity) {
                return commodity.equals(aCommodity) && !aCommodity.isCheckedout();
            }
        });

        if (theCommodity.isPresent()) {
            theCommodity.get().setCheckedout(true);
        }

        return theCommodity;
    }

    protected Predicate<T> checkedout() {
        return new Predicate<T>() {
            @Override
            public boolean apply(T commodity) {
                return commodity.isCheckedout();
            }
        };
    }

    protected ImmutableList<T> checkedoutCommodities() {
        return from(commodities).filter(checkedout()).toList();
    }

    protected ImmutableList<T> notCheckedoutCommodities() {
        return from(commodities).filter(not(checkedout())).toList();
    }

}
