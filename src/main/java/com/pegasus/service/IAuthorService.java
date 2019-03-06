package com.pegasus.service;


import com.pegasus.domain.Author;

public interface IAuthorService {

	int AddAuthor(Author author);

	Author findAuthorById(int id);
}
