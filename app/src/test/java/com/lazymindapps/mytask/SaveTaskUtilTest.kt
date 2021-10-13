package com.lazymindapps.mytask

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SaveTaskUtilTest{

    @Test
    fun emptyTitleReturnFalse(){
        val result = SaveTaskUtil.validateTaskInput(
            "",
            "doing task"
        )
        assertThat(result).isFalse()

    }
     @Test
    fun properFieldDataReturnTrue(){
        val result = SaveTaskUtil.validateTaskInput(
            "Book ticket",
            "From Kathmandu to Thailand"
        )
        assertThat(result).isTrue()

    }




}