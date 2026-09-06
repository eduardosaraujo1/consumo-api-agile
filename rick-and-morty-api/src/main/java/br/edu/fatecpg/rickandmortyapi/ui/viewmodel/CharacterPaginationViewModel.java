package br.edu.fatecpg.rickandmortyapi.ui.viewmodel;

import br.edu.fatecpg.rickandmortyapi.data.CharacterRepository;
import java.util.List;

public class CharacterPaginationViewModel {

    private static final int ROWS_PER_PAGE = 20;

    private final CharacterRepository _repo;

    private int _currentPage = 1;
    private int _cachedPage = 1;
    private List<Character> cache;

    public CharacterPaginationViewModel() {
        _repo = new CharacterRepository();
    }
}
