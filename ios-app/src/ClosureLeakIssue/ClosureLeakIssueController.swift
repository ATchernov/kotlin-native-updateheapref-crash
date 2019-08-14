//
//  ClosureLeakIssueController.swift
//  TestProj
//
//  Created by Andrey Tchernov on 14/08/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class ClosureLeakIssueViewController: UIViewController, ClosureLeakIssueTestClosureInterface {
  @IBOutlet private weak var stackView: UIStackView!
  
  func itemTapped(title: String) {
    print("\(title) tapped")
  }
  
  private var model: AnyObject!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    //MPP
      let testModel = ClosureLeakIssueNativeTest(
      withDispatcher: NativeDispatcher(withListener: self),
      andFactory: ItemsFactory())
    
    //Swift
//    let testModel = ClosureLeakIssueTest(eventsDispatcher: EventsDispatcher(mListener: self),
//                                        itemsFactory: ItemsFactory())
    //get elements
    testModel.getItems().compactMap({ $0 as? MyTestItem }).map({ item in
      let label = TappableLabel(frame: CGRect(x: 0, y: 0, width: 200, height: 40))
      label.text = item.title
      label.textAlignment = .center
      //reassign onTap
      label.onTap = item.onTap
      label.isUserInteractionEnabled = true
      return label
    }).forEach {
      stackView.addArrangedSubview($0)
    }
    
    self.model = testModel
  }
  
  deinit {
    print("ClosureLeakIssueViewController - deinit")
  }
}

class MyTestItem: AbstractItem {
  let title: String
  let onTap: (() -> Void)
  
  init(withTitle: String, andTap: @escaping (() -> Void)) {
    self.title = withTitle
    self.onTap = andTap
  }
}

class TappableLabel: UILabel {
  var onTap: (() -> Void)?
  
  override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
    onTap?()
  }
}

class ItemsFactory: ClosureLeakIssueTestItemsFactory {
  func createDataItem(title: String, onTap: @escaping () -> Void) -> AbstractItem {
    return MyTestItem(withTitle: title, andTap: onTap)
  }
}
