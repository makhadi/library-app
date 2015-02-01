package com.npu.libraryapp.services;

import com.npu.libraryapp.domain.Librarian;

public interface LibrarianService {
	public Librarian addLibrarian(Librarian libr);
	public Librarian updateLibrarian(Librarian libr);
	public int deleteLibrarian(int librarianId);
	public Librarian getLibrarian(int librarianId);
	public int checkLibrarian(String emailId, String password);
}
