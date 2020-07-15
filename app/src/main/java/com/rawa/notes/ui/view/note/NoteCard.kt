package com.rawa.notes.ui.view.note

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.rawa.notes.R
import com.rawa.notes.domain.Note
import com.rawa.notes.ui.getAttr
import kotlinx.android.synthetic.main.notesrow_note.view.*

class NoteCard(context: Context, attr: AttributeSet? = context.getAttr(R.layout.notesrow_note), defStyleAttr: Int = 0) :
    FrameLayout(context, attr, defStyleAttr) {

    private var noteId = -1L

    init {
        LayoutInflater.from(context).inflate(R.layout.notesrow_note, this)
    }

    fun render(note: Note) {
        this.noteId = note.id
        tv_note_title.text = note.title
        tv_note_title.isGone = note.title.isBlank()

        tv_note_text.text = note.text
        tv_note_text.isGone = note.text.isBlank()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        mcv_note_card.setOnClickListener(l)
    }
}
