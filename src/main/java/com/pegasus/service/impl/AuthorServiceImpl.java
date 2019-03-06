package com.pegasus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pegasus.dao.AuthorMapper;
import com.pegasus.domain.Author;
import com.pegasus.service.IAuthorService;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class AuthorServiceImpl implements IAuthorService{

	@Autowired
	private AuthorMapper authorMapping;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS,readOnly=true)
	public int AddAuthor(Author author) {

		int result = authorMapping.insertAuthor(author);
		
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS,readOnly =true)
	public Author findAuthorById(int id) {
		
		Author author = authorMapping.selectAuthorById(id);
		
		return author;
	}
	
}
