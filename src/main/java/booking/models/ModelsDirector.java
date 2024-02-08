package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ModelsDirector {
    ModelsBuilder model = null;
    ModelsBuilderWithId modelWithId = null;
    public ModelsDirector(ModelsBuilder ms){
        model = ms;
    }
    public ModelsDirector(ModelsBuilderWithId ms){
        modelWithId = ms;
    }
    public void construct(Long UserId, Long FieldId, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd){
        model.setUser(UserId);
        model.setField(FieldId);
        model.setNumPeople(numPeople);
        model.setDate(date);
        model.setTimeStart(timeStart);
        model.setTimeEnd(timeEnd);
    }
    public void construct(Long id, Long UserId, Long FieldId, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd){
        modelWithId.setId(id);
        modelWithId.setUser(UserId);
        modelWithId.setField(FieldId);
        modelWithId.setNumPeople(numPeople);
        modelWithId.setDate(date);
        modelWithId.setTimeStart(timeStart);
        modelWithId.setTimeEnd(timeEnd);
    }
    public ModelsBuilderWithId getModelWithId(){
        return modelWithId.clone();
    }
    public ModelsBuilder getModel(){
        return model.clone();
    }
}
