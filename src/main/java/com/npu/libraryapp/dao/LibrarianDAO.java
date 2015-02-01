package com.npu.libraryapp.dao;

import com.npu.libraryapp.domain.Librarian;

public interface LibrarianDAO {
	public void addLibrarian(Librarian libr);
	public int updateLibrarian(Librarian libr);
	public int deleteLibrarian(int librarianId);
	public int checkLibrarian(String emailId, String password);
	public Librarian getLibrarian(int librarianId);
}
