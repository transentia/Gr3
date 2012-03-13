package gr3

action(id: 'closeAction',
        name: 'Close',
        closure: controller.closeWithoutSavingAction,
        shortDescription: 'Close window without saving.'
)
action(id: 'saveAction',
        name: 'Save',
        mnemonic: 'S',
        smallIcon: builder.imageIcon("/disk.png"),
        accelerator: shortcut('S'),
        closure: controller.savePreferencesAction,
        shortDescription: 'Save Preferences and Close window.'
)
