package org.springframework.data.mongodb.examples.music;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;




/**
 * Test case to show the usage of {@link AlbumRepository} and thus the Mongo
 * repository support in general.
 * 
 * @author Oliver Gierke
 */
@ContextConfiguration
public class AlbumRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	AlbumRepository repository;

	@Before
	public void purgeRepository() {
		repository.deleteAll();
		super.setUp();
	}

	@Test
	public void createAlbum() throws Exception {

		repository.save(albums);
		assertSingleGruxAlbum(repository.findOne(bigWhiskey.getId()));
	}


	@Test
	public void findsAlbumByConcreteTrackName() throws Exception {

		repository.save(albums);
		
		List<Album> findByTracksTitle = repository.findByTitle("Big Whiskey and the Groo Grux King");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>:"+findByTracksTitle );
		List<Album> findByTracksTitle1 = repository.findByArtist("Dave Matthews Band");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>:"+findByTracksTitle1 );
		
		assertSingleGruxAlbum(repository.findByTracksName("Grux"));

		List<Album> albums = repository.findByTracksName("Foo");
		assertThat(albums.isEmpty(), is(true));
	}


	@Test
	public void findsAllAlbumsByTrackNameLike() throws Exception {

		repository.save(albums);
		assertBothAlbums(repository.findByTracksNameLike("*it*"));
	}


	@Test
	public void findsAlbumsByTrackRating() throws Exception {

		bigWhiskey.getTracks().get(4).setRating(Stars.FOUR);
		repository.save(albums);

		assertSingleGruxAlbum(repository
				.findByTracksRatingGreaterThan(Stars.THREE));

		List<Album> albums =
			repository.findByTracksRatingGreaterThan(Stars.FOUR);
		assertThat(albums.isEmpty(), is(true));
	}


	private void assertSingleGruxAlbum(List<Album> albums) {

		assertThat(albums, is(notNullValue()));
		assertThat(albums.size(), is(1));
		assertThat(albums.get(0), is(notNullValue(Album.class)));
		assertSingleGruxAlbum(albums.get(0));
	}
}
