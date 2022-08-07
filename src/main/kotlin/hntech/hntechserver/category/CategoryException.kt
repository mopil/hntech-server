package hntech.hntechserver.category

import hntech.hntechserver.utils.config.MAX_MAIN_CATEGORY_COUNT

const val CATEGORY_NOT_FOUND = "해당 카테고리가 존재하지 않습니다."
const val DUPLICATE_CATEGORY_NAME = "해당 이름의 카테고리가 이미 존재합니다."
const val MAXIMUM_NUMBER_OF_CATEGORIES = "메인에 보여질 카테고리는 ${MAX_MAIN_CATEGORY_COUNT}개까지만 선택 가능합니다."
const val NO_CATEGORY_IMAGE = "카테고리 사진을 등록해주세요."

class CategoryException(message: String) : RuntimeException(message)