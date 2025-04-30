package org.sopt.at.presentation.model

enum class TabGenreContent(
    val bannerImages: List<String>,
    val posterImages: List<String>
) {
    DRAMA(
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
        bannerImages = listOf("https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250307/0840/P001768874.jpg/dims/resize/F_webp,480"),
        posterImages = listOf(
            "https://tn.tv.nate.com/unsafe/376x540/https://fs.jtbc.co.kr/joydata/CP00000001/prog/enter/divorcecamp2/img/20240813_102635_402.jpg",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250224/0240/P001768309.jpg/dims/resize/480",
            "https://image3.tple.co.kr/_tpleStory/image/0001459/p0001459167178171825964.gif"
        )
    ),
    MOVIE(
        bannerImages = listOf("https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20250411/0445/M000378588.jpg/dims/resize/F_webp,480"),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20250411/0445/M000378588.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20240619/0540/M000377290.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20241004/0120/M000250033.jpg/dims/resize/F_webp,400"
        )
    ),
    SPORTS(
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
        bannerImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20160131/P000241379.jpg/dims/resize/F_webp,400"
        ),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20200902/P001319056.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240731/0016/P001759776.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caim/CAIM2100/ko/20250411/0445/M000378588.jpg/dims/resize/F_webp,480",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231218/1132/P001469366.png/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20160131/P000241379.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231031/2340/P001682579.jpg/dims/resize/F_webp,400"
        )
    ),
    NEWS(
        bannerImages = listOf("https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240311/1005/P001152940.jpg/dims/resize/F_webp,400"),
        posterImages = listOf(
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231028/1335/P000121860.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250310/0920/P001768977.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250311/0155/P001769014.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20250307/0155/P001768847.jpg/dims/resize/F_webp,400",
            "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231026/1315/P001194141.jpg/dims/resize/F_webp,400"
        )
    ),
    DEFAULT(
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
    );

    companion object {
        fun getGenreById(index: Int?): TabGenreContent? {
            if (index == null) return DEFAULT
            return entries.find {
                it.ordinal == index
            }
        }
    }
}