package gr3

action(id: 'playAction',
        name: 'Play',
        closure: controller.playAction,
        shortDescription: 'Play',
        enabled: bind { model.playButtonEnabled }
)
action(id: 'exitAction',
        name: 'Exit',
        closure: controller.exitAction,
        mnemonic: 'X',
)
action(id: 'aboutAction',
        name: 'About',
        mnemonic: 'A',
        closure: controller.aboutAction
)
action(id: 'preferencesAction',
        name: 'Preferences',
        mnemonic: 'P',
        closure: controller.preferencesAction
)
