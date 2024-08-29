package com.example.homeworkday11

object ItemDataManager {
    private val imageUrls = listOf(
        "https://cdn.pixabay.com/photo/2024/07/23/13/08/moon-8915326_1280.png",
        "https://cdn.pixabay.com/photo/2024/08/03/02/32/drink-8941028_640.png",
        "https://cdn.pixabay.com/photo/2024/07/08/05/41/girl-8880144_640.png",
        "https://cdn.pixabay.com/photo/2023/08/17/08/55/ice-cream-8195933_640.png",
        "https://cdn.pixabay.com/photo/2019/10/27/07/37/watermelon-4580910_640.png",
        "https://cdn.pixabay.com/photo/2024/06/22/20/06/eiffel-tower-8846952_640.png",
        "https://cdn.pixabay.com/photo/2023/11/20/13/21/shape-8401083_640.png",
        "https://cdn.pixabay.com/photo/2023/12/07/11/04/girl-8435329_640.png",
        "https://cdn.pixabay.com/photo/2023/10/14/09/20/mountains-8314422_640.png",
        "https://cdn.pixabay.com/photo/2023/08/07/13/40/flowers-8175044_640.png"
    )

    private val texts = listOf(
        "秋风萧瑟，落叶随风舞，思绪纷飞中，心绪渐平静。",
        "青山隐隐，白云悠悠，水墨丹青绘，心中故乡梦。",
        "晴川历历汉阳树，芳草萋萋鹦鹉洲，岁月静好。",
        "月落乌啼霜满天，江枫渔火对愁眠，清秋无尽意。",
        "小桥流水人家，柳丝轻垂拂波光，静谧春日里。",
        "江南水乡，烟雨朦胧中，青瓦白墙入画来。",
        "青山绿水绕村郭，春风吹拂麦田香，心生悠然情。",
        "落霞与孤鹜齐飞，秋水共长天一色，画意诗情间。",
        "桃花流水窅然去，别有天地非人间，芳菲满春意。",
        "云卷云舒花自开，风轻雨细润心田，岁月静流淌。"
    )

    val items by lazy {
        val result = mutableListOf<Item>()
        val maxSize = maxOf(imageUrls.size, texts.size)

        for (i in 0 until maxSize) {
            if (i < texts.size) {
                result.add(Item.TextItem(texts[i], i % 2 == 0))
            }
            if (i < imageUrls.size) {
                result.add(Item.ImageItem(imageUrls[i], i % 2 == 0))
            }
        }
        result
    }
}

