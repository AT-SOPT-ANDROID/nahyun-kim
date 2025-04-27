package org.sopt.at.presentation.home.model

enum class TabGenreResource(
    val genreId: Int?,
    val bannerImages: List<String>,
    val posterImages: List<String>
) {
    DEFAULT(
        genreId = null,
        bannerImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250326/0511/P001769546.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250320/0636/P001768976.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250212/0755/P001767988.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1110/ko/20250408/0916/P001769110.jpg/dims/resize/F_webp,400"
        ),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250410/0706/P001770178.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250320/0110/P001769310.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240814/1707/P001760343.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1130/ko/20250414/0643/P001768976.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231030/0505/P000643144.jpg/dims/resize/F_webp,480"
        )
    ),
    DRAMA(
        genreId = 0,
        bannerImages = listOf("https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250320/0645/P001768759.jpg/dims/resize/F_webp,480"),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1130/ko/20250414/0643/P001768976.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250320/0110/P001769310.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1160/ko/20250414/0643/P001769546.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250228/0921/P001768526.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250212/0755/P001767988.jpg/dims/resize/F_webp,480"
        )
    ),
    VARIETY(
        genreId = 1,
        bannerImages = listOf("https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250307/0840/P001768874.jpg/dims/resize/F_webp,480"),
        posterImages = listOf(
            "https://tn.tv.nate.com/unsafe/376x540/https://fs.jtbc.co.kr/joydata/CP00000001/prog/enter/divorcecamp2/img/20240813_102635_402.jpg",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250224/0240/P001768309.jpg/dims/resize/480",
            "https://image3.tple.co.kr/_tpleStory/image/0001459/p0001459167178171825964.gif"
        )
    ),
    MOVIE(
        genreId = 2,
        bannerImages = listOf("https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9791141157050.jpg"),
        posterImages = listOf(
            "https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/9791141157050.jpg",
            "https://design.co.kr/wp-content/uploads/2024/03/%ED%8C%8C%EB%AC%98_%EC%BA%90%EB%A6%AD%ED%84%B0-%ED%8F%AC%EC%8A%A4%ED%84%B0_%EA%B9%80%EA%B3%A0%EC%9D%80_%EC%A0%84%EB%8B%AC%EC%9A%A9-832x1189.jpg",
            "https://cdn.mhns.co.kr/news/photo/201908/264341_368957_1751.png"
        )
    ),
    SPORTS(
        genreId = 3,
        bannerImages = listOf(
            "https://blog.kakaocdn.net/dn/bfRO7x/btsMyAAtsrv/1MYexjkKh5aC1lbmlM6iKK/img.jpg",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250310/0125/P001768756.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250320/2326/P001769357.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250309/1001/P001768944.jpg/dims/resize/F_webp,480"
        ),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caic/CAIC3000/ko/20250220/0829/C52279.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caic/CAIC3000/ko/20250220/0827/C52273.jpg/dims/resize/F_webp,400",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgvLeuyFcr0AyeVO-5JVsj6KvjwXro0AurEuRc_AFcHdXdzQ807jsMQmLlv2riF2VQ7ac&usqp=CAU",
            "https://mir-s3-cdn-cf.behance.net/projects/404/fe8e5c82719985.Y3JvcCw5NTcsNzQ5LDIxLDE4Mg.png"
        )
    ),
    ANIMATION(
        genreId = 4,
        bannerImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20250411/0445/M000378588.jpg/dims/resize/F_webp,480"
        ),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20250411/0445/M000378588.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231130/0805/P000610641.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240731/0016/P001759776.jpg/dims/resize/F_webp,400"
        )
    ),
    NEWS(
        genreId = 5,
        bannerImages = listOf("https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240311/1005/P001152940.jpg/dims/resize/F_webp,400"),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231028/1335/P000121860.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250310/0920/P001768977.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250311/0155/P001769014.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250307/0155/P001768847.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231026/1315/P001194141.jpg/dims/resize/F_webp,400"
        )
    );

    companion object {
        fun getDataById(index: Int?): TabGenreResource? {
            return entries.find {
                it.genreId == index
            }
        }
    }
}