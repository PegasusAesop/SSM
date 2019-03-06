package com.pegasus.dao;

import com.pegasus.domain.Author;

public interface AuthorMapper {

	int insertAuthor(Author author);

	Author selectAuthorById(int id);
}
