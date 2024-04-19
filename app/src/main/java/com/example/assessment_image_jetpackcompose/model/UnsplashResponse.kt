package com.example.assessment_image_jetpackcompose.model

data class UnsplashResponse(
	val topicSubmissions: TopicSubmissions? = null,
	val currentUserCollections: List<Any?>? = null,
	val color: String? = null,
	val sponsorship: Any? = null,
	val createdAt: String? = null,
	val description: String? = null,
	val likedByUser: Boolean? = null,
	val urls: Urls? = null,
	val alternativeSlugs: AlternativeSlugs? = null,
	val altDescription: String? = null,
	val updatedAt: String? = null,
	val width: Int? = null,
	val blurHash: String? = null,
	val assetType: String? = null,
	val links: Links? = null,
	val id: String? = null,
	val promotedAt: String? = null,
	val user: User? = null,
	val slug: String? = null,
	val breadcrumbs: List<Any?>? = null,
	val height: Int? = null,
	val likes: Int? = null
)

data class Film(
	val status: String? = null
)

data class AlternativeSlugs(
	val de: String? = null,
	val ko: String? = null,
	val pt: String? = null,
	val ja: String? = null,
	val en: String? = null,
	val it: String? = null,
	val fr: String? = null,
	val es: String? = null
)

data class Links(
	val followers: String? = null,
	val portfolio: String? = null,
	val following: String? = null,
	val self: String? = null,
	val html: String? = null,
	val photos: String? = null,
	val likes: String? = null,
	val download: String? = null,
	val downloadLocation: String? = null
)



data class BlackAndWhite(
	val approvedOn: String? = null,
	val status: String? = null
)

data class ArchitectureInterior(
	val status: String? = null
)

data class Experimental(
	val approvedOn: String? = null,
	val status: String? = null
)

data class TopicSubmissions(
	val fashionBeauty: FashionBeauty? = null,
	val film: Film? = null,
	val experimental: Experimental? = null,
	val streetPhotography: StreetPhotography? = null,
	val architectureInterior: ArchitectureInterior? = null,
	val blackAndWhite: BlackAndWhite? = null
)

data class Urls(
	val small: String? = null,
	val smallS3: String? = null,
	val thumb: String? = null,
	val raw: String? = null,
	val regular: String? = null,
	val full: String? = null
)

data class StreetPhotography(
	val status: String? = null
)

data class ProfileImage(
	val small: String? = null,
	val large: String? = null,
	val medium: String? = null
)

data class FashionBeauty(
	val status: String? = null
)

data class User(
	val totalPhotos: Int? = null,
	val acceptedTos: Boolean? = null,
	val social: Social? = null,
	val twitterUsername: Any? = null,
	val lastName: String? = null,
	val bio: String? = null,
	val totalLikes: Int? = null,
	val portfolioUrl: Any? = null,
	val profileImage: ProfileImage? = null,
	val updatedAt: String? = null,
	val totalPromotedIllustrations: Int? = null,
	val forHire: Boolean? = null,
	val name: String? = null,
	val totalPromotedPhotos: Int? = null,
	val location: String? = null,
	val links: Links? = null,
	val totalCollections: Int? = null,
	val id: String? = null,
	val totalIllustrations: Int? = null,
	val firstName: String? = null,
	val instagramUsername: String? = null,
	val username: String? = null
)

data class Social(
	val twitterUsername: Any? = null,
	val paypalEmail: Any? = null,
	val instagramUsername: String? = null,
	val portfolioUrl: Any? = null
)

