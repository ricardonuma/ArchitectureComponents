package com.example.ricardonuma.architecturecomponents.View.Note;

import android.os.Bundle;

import androidx.navigation.NavArgs;

import kotlin.jvm.JvmStatic;

public class NoteListFragmentArgs implements NavArgs {

//    String taskId;
//
//    public NoteListFragmentArgs(String taskId) {
//        this.taskId = taskId;
//    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
//        result.putString("taskId", this.taskId);
        return result;
    }

    @JvmStatic
    public NoteListFragmentArgs fromBundle(Bundle bundle) {
        bundle.setClassLoader(NoteListFragmentArgs.class.getClassLoader());
        String __taskId;
        if (bundle.containsKey("taskId")) {
            __taskId = bundle.getString("taskId");
            if (__taskId == null) {
                throw new IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.");
            }
        } else {
            throw new IllegalArgumentException("Required argument \"taskId\" is missing and does not have an android:defaultValue");
        }
        return new NoteListFragmentArgs(/*__taskId*/);
    }

//    data class TaskDetailFragmentArgs(val taskId: String) : NavArgs
//
//    {
//        fun toBundle():Bundle {
//            val result = Bundle()
//            result.putString("taskId", this.taskId)
//            return result
//        }
//
//        companion object {
//            @JvmStatic
//            fun fromBundle(bundle: Bundle): TaskDetailFragmentArgs {
//                bundle.setClassLoader(TaskDetailFragmentArgs::class.java.classLoader)
//                val __taskId : String?
//                if (bundle.containsKey("taskId")) {
//                    __taskId = bundle.getString("taskId")
//                    if (__taskId == null) {
//                        throw IllegalArgumentException("Argument \"taskId\" is marked as non-null but was passed a null value.")
//                    }
//                } else {
//                    throw IllegalArgumentException("Required argument \"taskId\" is missing and does not have an android:defaultValue")
//                }
//                return TaskDetailFragmentArgs(__taskId)
//            }
//        }
//    }
}
