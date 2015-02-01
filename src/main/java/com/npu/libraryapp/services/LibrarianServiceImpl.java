package com.npu.libraryapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npu.libraryapp.dao.LibrarianDAO;
import com.npu.libraryapp.domain.Librarian;

@Service("librarianService")
@Transactional(readOnly=true)
public class LibrarianServiceImpl implements LibrarianService {
	
	@Autowired
	@Qualifier("librarianDaoJdbc")
	LibrarianDAO librarianDAO;
	
	@Override
	@Transactional(readOnly=false)
	public Librarian addLibrarian(Librarian libr){
		//Librarian libr = new Librarian(firstName, lastName, emailId, password);
		librarianDAO.addLibrarian(libr);
		return libr;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Librarian updateLibrarian(Librarian libr){
		
		librarianDAO.updateLibrarian(libr);
		
		return libr;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int deleteLibrarian(int librarianId){
		return librarianDAO.deleteLibrarian(librarianId);
	}
	
	@Override
	public Librarian getLibrarian(int librarianId){
		return librarianDAO.getLibrarian(librarianId);
	}
	
	@Override
	public int checkLibrarian(String emailId, String password) {
		return librarianDAO.checkLibrarian(emailId, password);
	}

}