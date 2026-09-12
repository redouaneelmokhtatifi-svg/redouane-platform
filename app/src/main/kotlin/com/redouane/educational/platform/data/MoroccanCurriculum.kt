package com.redouane.educational.platform.data

import com.redouane.educational.platform.data.models.*

/**
 * المحتوى التعليمي المغربي الحقيقي
 * المصادر:
 * 1. وزارة التربية الوطنية والتعليم الأولي والرياضة
 * 2. TelmidTICE
 * 3. AlloSchool (مع الإشارة للمصدر)
 */
object MoroccanCurriculum {

    // المستويات التعليمية
    val educationLevels = listOf(
        EducationLevel(
            id = "primary",
            nameAr = "التعليم الابتدائي",
            nameEn = "Primary Education",
            icon = "📚",
            order = 1,
            source = "وزارة التربية الوطنية"
        ),
        EducationLevel(
            id = "secondary",
            nameAr = "التعليم الإعدادي",
            nameEn = "Secondary Education",
            icon = "📖",
            order = 2,
            source = "وزارة التربية الوطنية"
        ),
        EducationLevel(
            id = "highschool",
            nameAr = "التعليم الثانوي التأهيلي",
            nameEn = "High School",
            icon = "🎓",
            order = 3,
            source = "وزارة التربية الوطنية"
        )
    )

    // السنوات الدراسية
    val grades = listOf(
        // الابتدائي
        Grade("g1", "primary", "السنة الأولى", "Grade 1", 1, "وزارة التربية الوطنية"),
        Grade("g2", "primary", "السنة الثانية", "Grade 2", 2, "وزارة التربية الوطنية"),
        Grade("g3", "primary", "السنة الثالثة", "Grade 3", 3, "وزارة التربية الوطنية"),
        Grade("g4", "primary", "السنة الرابعة", "Grade 4", 4, "وزارة التربية الوطنية"),
        Grade("g5", "primary", "السنة الخامسة", "Grade 5", 5, "وزارة التربية الوطنية"),
        Grade("g6", "primary", "السنة السادسة", "Grade 6", 6, "وزارة التربية الوطنية"),
        
        // الإعدادي
        Grade("g7", "secondary", "السنة الأولى إعدادي", "Grade 7", 1, "وزارة التربية الوطنية"),
        Grade("g8", "secondary", "السنة الثانية إعدادي", "Grade 8", 2, "وزارة التربية الوطنية"),
        Grade("g9", "secondary", "السنة الثالثة إعدادي", "Grade 9", 3, "وزارة التربية الوطنية"),
        
        // الثانوي التأهيلي
        Grade("g10", "highschool", "السنة الأولى بكالوريا", "Grade 10", 1, "وزارة التربية الوطنية"),
        Grade("g11", "highschool", "السنة الثانية بكالوريا", "Grade 11", 2, "وزارة التربية الوطنية")
    )

    // الشعب والمسالك
    val streams = listOf(
        // الإعدادي - عام
        Stream("s1", "g7", "عام", "General", StreamType.GENERAL, 1, "وزارة التربية الوطنية"),
        Stream("s2", "g8", "عام", "General", StreamType.GENERAL, 1, "وزارة التربية الوطنية"),
        Stream("s3", "g9", "عام", "General", StreamType.GENERAL, 1, "وزارة التربية الوطنية"),
        
        // الثانوي - آداب
        Stream("s4", "g10", "آداب وعلوم إنسانية", "Arts", StreamType.ARTS, 1, "وزارة التربية الوطنية"),
        Stream("s5", "g11", "آداب وعلوم إنسانية", "Arts", StreamType.ARTS, 1, "وزارة التربية الوطنية"),
        
        // الثانوي - علوم رياضية
        Stream("s6", "g10", "علوم رياضية", "Science", StreamType.SCIENCE, 2, "وزارة التربية الوطنية"),
        Stream("s7", "g11", "علوم رياضية", "Science", StreamType.SCIENCE, 2, "وزارة التربية الوطنية"),
        
        // الثانوي - علوم الحياة والأرض
        Stream("s8", "g10", "علوم الحياة والأرض", "Life Sciences", StreamType.SCIENCE_LIFE, 3, "وزارة التربية الوطنية"),
        Stream("s9", "g11", "علوم الحياة والأرض", "Life Sciences", StreamType.SCIENCE_LIFE, 3, "وزارة التربية الوطنية")
    )

    // المواد الدراسية
    val subjects = listOf(
        // الإعدادي - المواد العامة
        Subject("sub1", "s1", "اللغة العربية", "Arabic Language", "قواعد اللغة والأدب", "🔤", "#FF6B6B", 1, "وزارة التربية الوطنية"),
        Subject("sub2", "s1", "اللغة الفرنسية", "French Language", "Grammaire et vocabulaire", "🗣️", "#4ECDC4", 2, "وزارة التربية الوطنية"),
        Subject("sub3", "s1", "الرياضيات", "Mathematics", "الجبر والهندسة", "🔢", "#F7B731", 3, "وزارة التربية الوطنية"),
        Subject("sub4", "s1", "العلوم الفيزيائية", "Physics", "الميكانيكا والكهربائية", "⚛️", "#5F27CD", 4, "وزارة التربية الوطنية"),
        Subject("sub5", "s1", "علوم الحياة والأرض", "Biology", "الخلية والتكاثر", "🧬", "#00D2D3", 5, "وزارة التربية الوطنية"),
        Subject("sub6", "s1", "التاريخ والجغرافيا", "History & Geography", "الحضارات والخرائط", "🗺️", "#FF9FF3", 6, "وزارة التربية الوطنية"),
        Subject("sub7", "s1", "التربية الإسلامية", "Islamic Education", "الفقه والحديث", "☪️", "#48DBFB", 7, "وزارة التربية الوطنية"),
        Subject("sub8", "s1", "التربية البدنية", "Physical Education", "الرياضة والصحة", "⚽", "#1DD1A1", 8, "وزارة التربية الوطنية"),
        Subject("sub9", "s1", "التربية الفنية", "Art Education", "الرسم والنحت", "🎨", "#FFA502", 9, "وزارة التربية الوطنية"),
        Subject("sub10", "s1", "تكنولوجيا المعلومات", "Computer Science", "البرمجة والشبكات", "💻", "#1abc9c", 10, "وزارة التربية الوطنية"),
        
        // الثانوي - آداب
        Subject("sub11", "s4", "اللغة العربية", "Arabic Literature", "الشعر والنثر", "📝", "#FF6B6B", 1, "وزارة التربية الوطنية"),
        Subject("sub12", "s4", "اللغة الفرنسية", "French Literature", "Littérature française", "📚", "#4ECDC4", 2, "وزارة التربية الوطنية"),
        Subject("sub13", "s4", "الفلسفة", "Philosophy", "الأنطولوجيا والإبستيمولوجيا", "💭", "#2C3E50", 3, "وزارة التربية الوطنية"),
        Subject("sub14", "s4", "التاريخ والجغرافيا", "History & Geography", "الحضارات المعاصرة", "🗺️", "#FF9FF3", 4, "وزارة التربية الوطنية"),
        Subject("sub15", "s4", "العلوم الإسلامية", "Islamic Studies", "الفقه المقارن", "☪️", "#48DBFB", 5, "وزارة التربية الوطنية"),
        
        // الثانوي - علوم
        Subject("sub16", "s6", "الرياضيات", "Mathematics", "التحليل والجبر", "🔢", "#F7B731", 1, "وزارة التربية الوطنية"),
        Subject("sub17", "s6", "الفيزياء والكيمياء", "Physics & Chemistry", "الديناميكا والتفاعلات", "⚗️", "#5F27CD", 2, "وزارة التربية الوطنية"),
        Subject("sub18", "s6", "العلوم الطبيعية", "Natural Sciences", "البيئة والتطور", "🌿", "#00D2D3", 3, "وزارة التربية الوطنية"),
        Subject("sub19", "s6", "اللغة العربية", "Arabic", "النحو والبلاغة", "📖", "#FF6B6B", 4, "وزارة التربية الوطنية"),
        Subject("sub20", "s6", "اللغة الفرنسية", "French", "Grammaire avancée", "🇫🇷", "#4ECDC4", 5, "وزارة التربية الوطنية")
    )
}
