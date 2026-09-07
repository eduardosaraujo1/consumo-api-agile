package br.edu.fatecpg.rickandmortyapi.ui.viewmodel;

import br.edu.fatecpg.rickandmortyapi.data.CharacterRepository;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.CharacterListUnreachableException;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.InvalidCharacterListResponseException;
import br.edu.fatecpg.rickandmortyapi.domain.model.SeriesCharacter;
import java.util.ArrayList;
import java.util.List;

public class CharacterPaginationViewModel {

    private static final int ROWS_PER_PAGE = 20;

    private final CharacterRepository _repo;

    private int _currentPage = 1;
    private int _cachedPage = 0;
    private String _nameQuery = null;
    private List<SeriesCharacter> _characterList = null;

    private boolean _hasFetchError = false;

    // Will include a "cachedItemCount" in the future to define how many pages there are.

    public CharacterPaginationViewModel() {
        _repo = new CharacterRepository();
    }

    public List<SeriesCharacter> getCharacterList() {
        _hasFetchError = false;

        try {
            if (_characterList == null || _cachedPage != _currentPage) {
                int limit = ROWS_PER_PAGE;
                int offset = ROWS_PER_PAGE * (_currentPage - 1);

                if (_nameQuery.isBlank()) {
                    _characterList = _repo.listCharacters(limit, offset);
                } else {
                    _characterList = _repo.listCharacters(
                        limit,
                        offset,
                        _nameQuery
                    );
                }

                _cachedPage = _currentPage;
            }
        } catch (
            CharacterListUnreachableException
            | InvalidCharacterListResponseException e
        ) {
            // It is possible we have just reached the end of the list and no error occurred
            // (I did not realize I'd need  registryCount to validate if the next fetch operation.
            // I'm not changing the repository yet I've spent enough time on that)
            // For now let's presume it actually went wrong, and show an error message

            _characterList = new ArrayList<SeriesCharacter>();
            _cachedPage = -1;
            // Only notify a fetch error if the problem is a user network issue and not an error code (i.e. 404 not found)
            _hasFetchError = e instanceof CharacterListUnreachableException;
        }

        return _characterList;
    }

    public boolean hasFetchError() {
        return _hasFetchError;
    }

    public boolean nextPage() {
        // In the future: compare with Math.ceil(cachedItemCount / ROWS_PER_PAGE)
        ++_currentPage;
        return true;
    }

    public boolean previousPage() {
        if (_currentPage <= 1) {
            return false;
        }

        --_currentPage;
        return true;
    }

    public int getPage() {
        return _currentPage;
    }

    public void setQuery(String q) {
        if (q == null || q.isBlank()) {
            clearQuery();
        } else {
            _nameQuery = q;
        }
    }

    public void clearQuery() {
        _nameQuery = null;
        _cachedPage = 0;
        _characterList = null;
    }

    public String getQuery() {
        return _nameQuery;
    }

    public boolean hasQuery() {
        return _nameQuery != null && !_nameQuery.isBlank();
    }
}
