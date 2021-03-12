package com.adib.eurekatest

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Eureka"

        private const val TABLE_CATEGORY = "CategoryTable"
        private const val CATEGORY_ID = "id"
        private const val CATEGORY_NAME = "name"
        private const val CATEGORY_IMAGE = "image"

        private const val TABLE_QUESTION = "QuestionTable"
        private const val QUESTION_ID = "qId"
        private const val QUESTION_TEXT = "question"
        private const val QUESTION_IMAGE = "qImage"
        private const val ANSWER_A = "answerA"
        private const val ANSWER_B = "answerB"
        private const val ANSWER_C = "answerC"
        private const val ANSWER_D = "answerD"
        private const val CORRECT_ANSWER = "correctAnswer"
        private const val IS_IMAGE_QUESTION =  "isImageQuestion"
        private const val QUESTION_CATEGORY_ID = "categoryId"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createCategoryTable = ("CREATE TABLE " + TABLE_CATEGORY + "("
                + CATEGORY_ID + " INTEGER PRIMARY KEY," + CATEGORY_NAME + " TEXT,"
                + CATEGORY_IMAGE + " TEXT)")
        db?.execSQL(createCategoryTable)

        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Art / Culture', 'https://thumbs.dreamstime.com/b/african-style-decorated-color-vase-isolated-over-white-background-decoration-culture-ethnic-art-tool-outline-vector-90143630.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Geography', 'https://thumbs.dreamstime.com/b/globe-earth-school-tools-study-geography-isolated-white-background-85163511.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Music', 'https://thumbs.dreamstime.com/b/portable-music-speaker-vector-icon-symbol-isolated-white-background-eps-174362116.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Economy', 'https://thumbs.dreamstime.com/b/white-background-colorful-display-computer-graphics-growth-economy-vector-illustration-112050622.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('History', 'https://preview.pixlr.com/images/800wm/1312/1/1312105852.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Nature', 'https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX2364506.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Film / TV', 'https://preview.pixlr.com/images/800wm/1460/2/1460111236.jpg')")
        db?.execSQL("INSERT INTO $TABLE_CATEGORY($CATEGORY_NAME, $CATEGORY_IMAGE) VALUES('Informatics', 'https://preview.pixlr.com/images/800wm/1312/2/1312114271.jpg')")

        val createQuestionTable = ("CREATE TABLE " + TABLE_QUESTION + "("
                + QUESTION_ID + " INTEGER PRIMARY KEY UNIQUE," + QUESTION_TEXT + " TEXT,"
                + QUESTION_IMAGE + " TEXT," + ANSWER_A + " TEXT," + ANSWER_B + " TEXT,"
                + ANSWER_C + " TEXT," + ANSWER_D + " TEXT," + CORRECT_ANSWER + " TEXT,"
                + IS_IMAGE_QUESTION + " INTEGER," + QUESTION_CATEGORY_ID + " INTEGER)")
        db?.execSQL(createQuestionTable)

        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $QUESTION_IMAGE, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Where is it?','https://www.austrianblog.com/media/images/paris-city-of-love.original.jpg','Paris','London','New York','Vatican','Paris',1, 2)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $QUESTION_IMAGE, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Where is it?','https://turkishairlines.ssl.cdn.sdlmedia.com/637128604553104030HA.jpg','Illinois','Alaska','Texas','New York','New York',1,2)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $QUESTION_IMAGE, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Where is it?','http://www.abc.net.au/news/image/6887602-3x2-700x467.jpg','Hongkong','Sydney','Hochiminh City','Singapore','Sydney',1,2)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('What is the capital of Turkey?','Hanoi','Ankara','Wakanda','Phnompenh','Ankara',0,2)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('What is the national animal of China?','Tiger','Nappa','Giant panda','Elephant','Giant panda',0,2)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Which Turkish city has the name of a cartoon character?','Superman','Ant-man','Spiderman','Batman','Batman',0,2)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('In which Spanish city did the Joan Miro museum open in 1975?','Gold colored','Barcelona','Botticelli','Baram Stoker','Barcelona',0,1)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Which Italian artist painted the Birth of Venus?','Gold colored','Barcelona','Botticelli','Baram Stoker','Botticelli',0,1)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Who was the original author of Dracula?','Gold colored','Barcelona','Botticelli','Baram Stoker','Baram Stoker',0,1)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('In which city is the famous Manneken Pis fountain?','Brussels','Barcelona','Botticelli','Baram Stoker','Brussels',0,1)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('Who is the inventor of photography?','Daguerre','Barcelona','Botticelli','Baram Stoker','Daguerre',0,1)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('What is the first book of the Old Testament?','Genesis','Leonardo Da Vinci','Liverpool','Gustave Eiffel','Genesis',0,1)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('This is multi choice question','Right Answer','Wrong Answer','Wrong Answer','Right Answer','Right Answer',0,3)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('This is multi choice question 2','Right Answer','Wrong Answer','Right Answer','Wrong Answer','Right Answer',0,3)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('This is multi choice question 3','Right Answer','Right Answer','Wrong Answer','Wrong Answer','Right Answer',0,3)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('This is multi choice question 4','Wrong Answer','Right Answer','Right Answer','Wrong Answer','Right Answer',0,3)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('This is multi choice question 5','Wrong Answer','Right Answer','Wrong Answer','Right Answer','Right Answer',0,3)")
        db?.execSQL("INSERT INTO $TABLE_QUESTION($QUESTION_TEXT, $ANSWER_A, $ANSWER_B, $ANSWER_C, $ANSWER_D, $CORRECT_ANSWER, $IS_IMAGE_QUESTION, $QUESTION_CATEGORY_ID) VALUES('This is multi choice question 6','Wrong Answer','Wrong Answer','Right Answer','Right Answer','Right Answer',0,3)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTION")
        onCreate(db)
    }

    fun viewCategory() : ArrayList<Category> {
        val categoryList = ArrayList<Category>()
        val allQuery = "SELECT * FROM $TABLE_CATEGORY"
        val db = this.readableDatabase
        val cursor: Cursor

        try {
            cursor = db.rawQuery(allQuery, null)
        } catch (e: SQLException) {
            db.execSQL(allQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var image: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(CATEGORY_ID))
                name = cursor.getString(cursor.getColumnIndex(CATEGORY_NAME))
                image = cursor.getString(cursor.getColumnIndex(CATEGORY_IMAGE))

                val eachCategory = Category(id = id, name = name, image = image)
                categoryList.add(eachCategory)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryList
    }

    fun viewQuestion(questionCategoryId: Int) : ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val questionQuery = "SELECT * FROM $TABLE_QUESTION WHERE $QUESTION_CATEGORY_ID=$questionCategoryId ORDER BY RANDOM() LIMIT 5"
        val db = this.readableDatabase
        val cursor: Cursor

        try {
            cursor = db.rawQuery(questionQuery, null)
        } catch (e: SQLException) {
            db.execSQL(questionQuery)
            return ArrayList()
        }

        var qId: Int
        var question: String
        var qImage: String
        var answerA: String
        var answerB: String
        var answerC: String
        var answerD: String
        var correctAnswer: String
        var isImageQuestion: Int
        var categoryId: Int

        if (cursor.moveToFirst()) {
            do {
                qId = cursor.getInt(cursor.getColumnIndex(QUESTION_ID))
                question = cursor.getString(cursor.getColumnIndex(QUESTION_TEXT))
                qImage = if (cursor.getString(cursor.getColumnIndex(QUESTION_IMAGE)) != null) cursor.getString(cursor.getColumnIndex(QUESTION_IMAGE))
                    else "image"
                answerA = cursor.getString(cursor.getColumnIndex(ANSWER_A))
                answerB = cursor.getString(cursor.getColumnIndex(ANSWER_B))
                answerC = cursor.getString(cursor.getColumnIndex(ANSWER_C))
                answerD = cursor.getString(cursor.getColumnIndex(ANSWER_D))
                correctAnswer = cursor.getString(cursor.getColumnIndex(CORRECT_ANSWER))
                isImageQuestion = cursor.getInt(cursor.getColumnIndex(IS_IMAGE_QUESTION))
                categoryId = cursor.getInt(cursor.getColumnIndex(QUESTION_CATEGORY_ID))

                val eachQuestion = Question(qId=qId, question=question, qImage=qImage,
                        answerA=answerA, answerB=answerB, answerC=answerC, answerD=answerD,
                        correctAnswer=correctAnswer, isImageQuestion=isImageQuestion,
                        categoryId=categoryId)
                questionList.add(eachQuestion)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return questionList
    }
}