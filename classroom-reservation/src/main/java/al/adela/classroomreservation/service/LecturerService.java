package al.adela.classroomreservation.service;

import al.adela.classroomreservation.model.Classroom;
import al.adela.classroomreservation.model.Lecturer;
import al.adela.classroomreservation.repository.ClassroomRepository;
import al.adela.classroomreservation.repository.LecturerRepository;
import al.adela.classroomreservation.utils.ApiReturnObject;
import al.adela.classroomreservation.utils.ClassroomStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    private final ClassroomRepository classroomRepository;

    public LecturerService(LecturerRepository lecturerRepository, ClassroomRepository classroomRepository){
        this.lecturerRepository = lecturerRepository;
        this.classroomRepository = classroomRepository;
    }

    public List<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }

    public ApiReturnObject loginLecturer(String username, String password) {

        log.info("Inside loginLecturer");
        log.info("username : " + username);
        log.info("password : " + password);

        List<Lecturer> allLecturers = lecturerRepository.findAll();
        ApiReturnObject returnObject = new ApiReturnObject();

        for (Lecturer lecturer : allLecturers){
            if(lecturer.getUsername().equals(username) && lecturer.getPassword().equals(password)){
                returnObject.setStatus(true);
                returnObject.setDescription("Login successful");
                return returnObject;
            }
        }

        returnObject.setStatus(false);
        returnObject.setDescription("Login denied");
        return returnObject;
    }

    public List<Classroom> getClassrooms(String lecturerId){

        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(Integer.parseInt(lecturerId));
        Lecturer lecturer = optionalLecturer.get();

        return lecturer.getClassroomList();
    }


    public ApiReturnObject reserveClassroom(String classroomId, String lecturerId){

        ApiReturnObject returnObject = new ApiReturnObject();

        try{
            Optional<Classroom> optionalClassroom = classroomRepository.findById(Integer.parseInt(classroomId));
            Classroom classroom = optionalClassroom.get();
            Optional<Lecturer> optionalLecturer = lecturerRepository.findById(Integer.parseInt(lecturerId));
            Lecturer lecturer = optionalLecturer.get();
            if(!classroom.getStatus().equals(ClassroomStatus.BUSY.getValue())){
                classroom.setStatus(ClassroomStatus.BUSY.getValue());
                classroom.setLecturer(lecturer);
                classroomRepository.save(classroom);
                returnObject.setStatus(true);
                returnObject.setDescription("Classroom reserved successfully");
                return returnObject;
            }else {
                returnObject.setStatus(false);
                returnObject.setDescription("Classroom reservation failed because it was already busy");
                return returnObject;
            }

        } catch (Exception e) {
            log.info("Exception while reserving classroom + " + e);
            e.printStackTrace();
            returnObject.setStatus(false);
            returnObject.setDescription("Classroom reservation failed");
            return returnObject;
        }
    }

    public ApiReturnObject freeClassroom(String classroomId, String lecturerId){

        ApiReturnObject returnObject = new ApiReturnObject();

        try{
            Optional<Classroom> optionalClassroom = classroomRepository.findById(Integer.parseInt(classroomId));
            Classroom classroom = optionalClassroom.get();
            Optional<Lecturer> optionalLecturer = lecturerRepository.findById(Integer.parseInt(lecturerId));
            Lecturer lecturer = optionalLecturer.get();

            if (classroom.getLecturer().getId().equals(Integer.parseInt(lecturerId))) {
                classroom.setStatus(ClassroomStatus.AVAILABLE.getValue());
                classroom.setLecturer(null);
                classroomRepository.save(classroom);
                returnObject.setStatus(true);
                returnObject.setDescription("Classroom was freed successfully");
                return returnObject;
            }else {
                returnObject.setStatus(false);
                returnObject.setDescription("This lecturer can't free this classroom");
                return returnObject;
            }


        } catch (Exception e) {
            log.info("Exception while freeing classroom + " + e);
            returnObject.setStatus(false);
            returnObject.setDescription("Classroom update did not work");
            return returnObject;
        }
    }
}
