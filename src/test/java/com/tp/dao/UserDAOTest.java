package com.tp.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tp.daos.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/appContext-test.xml" })
public class UserDAOTest {
	@Autowired
	UserDAO dao;

	@Test
	public void uneEntréeQuiNexistePas() {
		assertNull(dao.find(10l));
	}

	@Test
	public void peutRécupérerThomas() {
		assertEquals("thomas", dao.find(1l).getName());
	}
	
	@Test
	public void testFindByName() {
		assertEquals(1, dao.findByName("thomas").getId());
	}

	@Test
	public void testFindByInvalidName() {
		assertNull(dao.findByName("unexistant"));
	}
}
