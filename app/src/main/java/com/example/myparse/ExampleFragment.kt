package com.example.myparse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_exfragment.*

class ExampleFragment constructor(pageImages: String, pageNames: String, pageDescriptions: String) : Fragment() {
    private var pageImage: String = ""
    private var pageName: String = ""
    private var pageDescription: String = ""

    init {
        pageImage = pageImages
        pageName = pageNames
        pageDescription = pageDescriptions
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        techName.text = pageName
        techDescription.text = pageDescription
        Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + pageImage).into(dynamicImageView)
    }
}