package me.vlad.sortingproject;

import me.vlad.sortingproject.sortingalgorithms.BackendProvider;
import me.vlad.sortingproject.sortingalgorithms.InvalidBackendException;
import me.vlad.sortingproject.ui.InvalidFrontendException;

public class Main {
    public static void main(String[] args) throws InvalidFrontendException, InvalidBackendException {
        SortManager sman = new SortManager();
        BackendProvider.listBackends();
        sman.start();
    }

}