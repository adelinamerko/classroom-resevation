package al.adela.classroomreservation.service;

import al.adela.classroomreservation.model.Classroom;
import al.adela.classroomreservation.repository.ClassroomRepository;
import al.adela.classroomreservation.utils.ApiReturnObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository){
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public ApiReturnObject addClassroom(int classroomNumber, int seatsNumber){
        ApiReturnObject returnObject = new ApiReturnObject();
        try{
            Classroom classroom = new Classroom();
            classroom.setClassroomNumber(classroomNumber);
            classroom.setSeatsNumber(seatsNumber);
            classroomRepository.save(classroom);

            returnObject.setStatus(true);
            returnObject.setDescription("Classroom added successfully");
            return returnObject;

        }catch (Exception e) {
            log.error("Exception while saving new classroom : " + e);
            returnObject.setStatus(false);
            returnObject.setDescription("Classroom can not be added");
            return returnObject;
        }
    }

    public ApiReturnObject removeClassroom(String classroomId) {

        ApiReturnObject returnObject = new ApiReturnObject();

        try{
            classroomRepository.deleteById(Integer.parseInt(classroomId));

            returnObject.setStatus(true);
            returnObject.setDescription("Classroom deleted successfully");
            return returnObject;

        } catch(Exception e) {
            log.info("Exception while removing classroom : " + e);
            returnObject.setStatus(false);
            returnObject.setDescription("Classroom can not be deleted");
            return returnObject;
        }
    }

    public Classroom updateClassroom(String classroomId, String status){

        try{
            Optional<Classroom> optionalClassroom = classroomRepository.findById(Integer.parseInt(classroomId));
            Classroom classroom = optionalClassroom.get();
            classroom.setStatus(status);
            return classroomRepository.save(classroom);
        } catch (Exception e) {
            log.error("Exception while updating status of classroom " + e);
            return null;
        }
    }
}
