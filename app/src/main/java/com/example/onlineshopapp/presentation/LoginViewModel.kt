package com.example.onlineshopapp.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.onlineshopapp.data.ShopListRepositoryImpl
import com.example.onlineshopapp.data.UserRepositoryImpl
import com.example.onlineshopapp.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel(application: Application): AndroidViewModel(application){

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputSurname = MutableLiveData<Boolean>()
    val errorInputSurname: LiveData<Boolean>
        get() = _errorInputSurname

    private val _errorInputPhone = MutableLiveData<Boolean>()
    val errorInputPhone: LiveData<Boolean>
        get() = _errorInputPhone

    private val _allIsValidate = MutableLiveData<Boolean>()
    val allIsValidate: LiveData<Boolean>
        get() = _allIsValidate


    private val regex = "[^а-яёА-ЯЁ]+"
    private val pattern = Pattern.compile(regex)

    private val regexPhone = "[^0-9]+"
    private val patternPhone = Pattern.compile(regexPhone)

    private val userRepository = UserRepositoryImpl(application)
    private val getUsersUseCase = GetUsersUseCase(userRepository)

    fun validateInput(name:String, surname:String, phone:String){
        var result = true
        if (name.isBlank() || surname.isBlank() || phone.isBlank()){
            result = false
        }
        if (errorInputName.value == true || errorInputSurname.value == true || errorInputPhone.value == true){
            result = false
        }
        _allIsValidate.value = result
    }

    fun resetErrorInputSymbolName(str:String){
        var m = pattern.matcher(str);
        _errorInputName.value = m.find()
    }

    fun resetErrorInputSymbolSurname(str:String){
        var m = pattern.matcher(str);
        _errorInputSurname.value = m.find()
    }

    fun resetErrorInputSymbolPhone(str:String){
        var m = patternPhone.matcher(str);
        _errorInputPhone.value = m.find()
    }

    fun getUsers() = getUsersUseCase.getUsers()

    private val shopListRepository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepository)

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = _user

    private val _userIsFind = MutableLiveData<Boolean>()
    val userIsFind: LiveData<Boolean>
        get() = _userIsFind

    private val getUserUseCase = GetUserUseCase(userRepository)
    private val addUserUseCase = AddUserUseCase(userRepository)

    fun getUser(name: String, surname: String, phone: String){
        viewModelScope.launch(Dispatchers.IO) {
            var item:User? = getUserUseCase.getUser(name, surname, phone)
            if (item == null){
                item = User(name,surname,phone)
                addUserUseCase.addUser(item)
                _user.postValue(item)
                _userIsFind.postValue(false)
            }else{
                _user.postValue(item)
                _userIsFind.postValue(true)
            }

        }
    }




}