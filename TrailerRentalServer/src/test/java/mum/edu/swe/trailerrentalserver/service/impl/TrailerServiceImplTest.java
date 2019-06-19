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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TrailerServiceImplTest {

    private Long actual;

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

//    @Before
//    public void setUp() throws Exception {
//        List<Trailer> r1 = new Trailer(1, "A4", "Uthopio park - 4A", 510f, 1, "feature", "image");
//        actual = r1..getTrailerId();
//
//        Mockito.when(trailerRepository.findAllByNumberContains("A4"))
//                .thenReturn(r1);
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void findAllByNumberContains() {
//    }
//
//    @Test
//    public void whenFindByName() {
//        String number = "A4";
//        Trailer found = trailerService.findAllByNumberContains(number);
//        assertEquals(found.getName(), name);
//    }
}
