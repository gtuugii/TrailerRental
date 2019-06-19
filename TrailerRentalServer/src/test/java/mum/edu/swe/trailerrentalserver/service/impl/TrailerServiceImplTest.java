package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.domain.Trailer;
import mum.edu.swe.trailerrentalserver.repository.TrailerRepository;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TrailerServiceImplTest {

    List<Trailer> actual = new ArrayList<Trailer>();

    @Autowired
    private TrailerService trailerService;

    @MockBean
    private TrailerRepository trailerRepository;


    @TestConfiguration
    static class TrailerRepositoryImplTestContextConfiguration{
        @Bean
        public TrailerService trailerService(){
            return new TrailerServiceImpl();
        }
    }

    @Before
    public void setUp() throws Exception {
        //Trailer t1 = new Trailer(1, "4A", "Uthopio park - 4A", 510f, 1, "feature", "image");
        //Trailer t2 = new Trailer(1, "4B", "Uthopio park - 4B", 500f, 1, "feature", "image");

        //actual.add(t1);
        //actual.add(t2);

        //Mockito.when(trailerRepository.findAllByNumberContains("4A"))
        //        .thenReturn(actual);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAllByNumberContains() {
    }

    @Test
    public void whenFindByName() {
        String number = "A4";
        List<Trailer> found = trailerService.findAllByNumberContains(number);
        assertEquals(found, actual);
    }
}
