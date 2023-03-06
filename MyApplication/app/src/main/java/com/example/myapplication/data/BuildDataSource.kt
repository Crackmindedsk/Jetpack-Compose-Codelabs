package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.BuildGridData

class BuildDataSource {
    fun BuildLoad():List<BuildGridData>{
        return listOf<BuildGridData>(
            BuildGridData(R.string.affirmation1,R.drawable.image1,R.string.o1),
            BuildGridData(R.string.affirmation2,R.drawable.image2,R.string.o2),
            BuildGridData(R.string.affirmation3,R.drawable.image3,R.string.o3),
            BuildGridData(R.string.affirmation4,R.drawable.image4,R.string.o4),
            BuildGridData(R.string.affirmation5,R.drawable.image5,R.string.o5),
            BuildGridData(R.string.affirmation6,R.drawable.image6,R.string.o6),
            BuildGridData(R.string.affirmation7,R.drawable.image7,R.string.o7),
            BuildGridData(R.string.affirmation8,R.drawable.image8,R.string.o8),
            BuildGridData(R.string.affirmation9,R.drawable.image9,R.string.o9),
            BuildGridData(R.string.affirmation10,R.drawable.image10,R.string.o10)
        )
    }
}