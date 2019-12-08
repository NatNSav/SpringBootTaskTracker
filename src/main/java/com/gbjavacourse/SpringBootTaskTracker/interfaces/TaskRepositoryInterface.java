package com.gbjavacourse.SpringBootTaskTracker.interfaces;

import com.gbjavacourse.SpringBootTaskTracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryInterface extends JpaRepository<Task,Long>, PagingAndSortingRepository<Task,Long>, JpaSpecificationExecutor<Task> {
    List<Task> findAll();
    List<Task> findByTitle(String title);
    //Page<Task> findByStatus_id(Long status_id);
    //Page<Task> findByOwner_id(Long owner_id);
    //Page<Task> findByStatus_idAndOwner_id(Long status_id,Long owner_id);

    //List<TaskDTO> findAllBy();

    //@Query("SELECT new com.gbjavacourse.SpringBootTaskTracker.entities.TaskDTO(i.id,i.title,i.owner,i.executer,i.description,i.status) FROM Task i where i.status_id=status_id")
    //List<com.gbjavacourse.SpringBootTaskTracker.entities.TaskDTO> findTaskDTOsByStatus(Long status_id);
}
