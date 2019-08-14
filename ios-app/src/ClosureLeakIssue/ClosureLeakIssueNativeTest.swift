//
//  NativeViewModel.swift
//  TestProj
//
//  Created by Andrey Tchernov on 14/08/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

//Like in MPP
class ClosureLeakIssueNativeTest: NSObject {
  private let dispatcher: NativeDispatcher<ClosureLeakIssueTestClosureInterface>
  private let itemsFactory: ClosureLeakIssueTestItemsFactory
  
  init(withDispatcher dispatcher: NativeDispatcher<ClosureLeakIssueTestClosureInterface>,
       andFactory factory: ClosureLeakIssueTestItemsFactory) {
    self.dispatcher = dispatcher
    self.itemsFactory = factory
  }
  
  func getItems() -> [AbstractItem] {
    return (1...10).map({ "Item #\($0)" }).map({ [weak self] item in
      itemsFactory.createDataItem(title: item, onTap: {
        self?.dispatcher.dispatchEvent({ $0.itemTapped(title: item) })
      }
    )
    })
  }
}

class NativeDispatcher<T> where T: AnyObject {
  private weak var listener: T?
  init(withListener: T) {
    self.listener = withListener
  }
  func dispatchEvent(_ event: (T) -> Void) {
    if let nListener = listener {
      event(nListener)
    }
  }
}
