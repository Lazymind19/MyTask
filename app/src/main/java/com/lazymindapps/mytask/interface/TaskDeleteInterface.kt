import com.lazymindapps.mytask.db.model.Task

interface TaskDeleteInterface {
    fun deleteTask(task: Task)
}