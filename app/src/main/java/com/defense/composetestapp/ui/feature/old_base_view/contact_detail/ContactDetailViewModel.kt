//package com.defense.composetestapp.ui.feature.old_base_view.contact_detail
//
//import androidx.lifecycle.SavedStateHandle
//import com.defense.composetestapp.data.usecase.ContactGetUseCase
//import com.defense.composetestapp.ui.base.BaseViewModel
//import com.defense.composetestapp.ui.feature.main.NavigationScreen
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class ContactDetailViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    contactGetUseCase: ContactGetUseCase
//) : BaseViewModel<ContactDetailViewState, ContactDetailViewEvent, ContactDetailViewAction>() {
//
//    private val args = ContactDetailArgs(
//        requireNotNull(
//            savedStateHandle.get<String>(
//                NavigationScreen.CONTACT_DETAIL.argNames()[0].name
//            )
//        ) { "Invalid args" }
//    )
//
//    private val contact = contactGetUseCase.perform(args.contactId)
//
//
//    init {
//    }
//
//    override fun createInitialState() = ContactDetailViewState(
//        title = "",
//        imageUrl = null,
//        email = "",
//        name = ""
//    )
//
//    override fun handleAction(action: ContactDetailViewAction) {
//    }
//
//    override fun onViewActive() {
//        updateState {
//            copy(
//                title = contact?.name ?: "",
//                imageUrl = contact?.picture?.largeUrl,
//                email = contact?.email,
//                name = contact?.name ?: ""
//            )
//        }
//    }
//}