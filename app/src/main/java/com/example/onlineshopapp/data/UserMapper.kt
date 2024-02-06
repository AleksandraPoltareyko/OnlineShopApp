package com.example.onlineshopapp.data

import com.example.onlineshopapp.domain.User

class UserMapper {
    fun mapEntityToDbModel(user: User) = UserDBModel(
        id = user.id,
        name = user.name,
        surname = user.surname,
        phone = user.phone,
        favorites = user.favorites
    )

    fun mapDbModelToEntity(userDBModel: UserDBModel) = User(
        id = userDBModel.id,
        name = userDBModel.name,
        surname = userDBModel.surname,
        phone = userDBModel.phone,
        favorites = userDBModel.favorites
    )

    fun mapListDbModelToListEntity(list: List<UserDBModel>) = list.map {
        mapDbModelToEntity(it)
    }
}