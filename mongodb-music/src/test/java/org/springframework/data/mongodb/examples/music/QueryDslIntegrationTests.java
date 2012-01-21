package org.springframework.data.mongodb.examples.music;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;



/**
 * Sample test case showing the usage of Querydsl predicates.
 *
 * @author Oliver Gierke
 */
@ContextConfiguration
public class QueryDslIntegrationTests extends AbstractIntegrationTest {

	@Autowired
	AlbumRepository repository;

	QAlbum album = QAlbum.album;
//	QTrack atrack = QTrack..track;

	@Override
	@Before
	public void setUp() {
		repository.deleteAll();
	}

	@Test
	public void invokesQuerydslPredicateCorrectly() {

		repository.save(albums);
		assertSinglePursuitAlbum(repository.findOne(album.title.eq("The Pursuit")));
		assertSinglePursuitAlbum(repository.findOne(album.artist.eq("Dave Matthews Band")));
//		assertSinglePursuitAlbum(repository.findOne(album.tracks.contains(tr));
//		assertSinglePursuitAlbum(repository.findOne(album.title.eq("The Pursuit")));
	}
}
