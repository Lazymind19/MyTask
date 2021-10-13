package com.lazymindapps.mytask


object SaveTaskUtil {

    /**
     * Rules:
     * the input is invalid if ..
     * .. the title is empty
     *
     *
     * the input is valid if..
     * ..title is not empty
     * .. only description is empty
     *
     *
      */


    fun validateTaskInput(
        title:String,
        description:String,
    ):Boolean{
       if (title.isNullOrBlank()){
           return false
       }
        if (title!=null){
            return true
        }



        return true
    }
}