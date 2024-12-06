import java.time.LocalDateTime;

/**
* @author Yunpeng Lyu
*
* Interface for managing meal plans.
*
*/
public interface MealPlanInterface {

    
    String createOrUpdateMealPlan(String patientID);

    void setMealPlanStatus(String patientID, boolean isApproved);

    boolean isMealPlanApproved(String patientID);

    void prepareMeals(String patientID);
}
