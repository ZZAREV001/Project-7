package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BidListServiceImplTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListServiceImpl bidListService;

    @Test
    public void findAllTest() {
        // Arrange
        BidList bidList = new BidList();
        List<BidList> expectedBidList = Collections.singletonList(bidList);
        Mockito.when(bidListRepository.findAll()).thenReturn(expectedBidList);

        // Act
        List<BidList> actualBidList = bidListService.findAll();

        // Assert
        assertEquals(expectedBidList, actualBidList);
    }

    @Test
    public void saveTest() {
        // Arrange
        BidList bidList = new BidList();

        // Act
        bidListService.save(bidList);

        // Assert
        Mockito.verify(bidListRepository).save(bidList);
    }

    @Test
    public void findByIdTest() {
        // Arrange
        BidList bidList = new BidList();
        Mockito.when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        // Act
        Optional<BidList> foundBidList = bidListService.findById(1);

        // Assert
        assertTrue(foundBidList.isPresent());
        assertEquals(bidList, foundBidList.get());
    }

    @Test
    public void deleteByIdTest() {
        // Arrange
        Integer id = 1;

        // Act
        bidListService.deleteById(id);

        // Assert
        Mockito.verify(bidListRepository).deleteById(id);
    }
}

