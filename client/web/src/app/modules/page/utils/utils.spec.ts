import {ComponentFixture, TestBed} from '@angular/core/testing';
import {createModalData} from './Types';


describe('Types', () => {

  it('should create', () => {
    let data = createModalData();
    expect(data).not.toBeNull();
    expect(data.data).toBeUndefined()
    expect(data.visible).toBe(false)
    expect(data.onCancel).not.toBeNull();
    expect(data.onOk).not.toBeNull();
    data.visible = true
    data.onCancel();
    expect(data.visible).toBe(false)
  });

  it('should create with init arg', () => {
    let data = createModalData<Date>({
      visible: true,
      data: new Date(),
      onOk: () => null,
      onCancel: () => null,
    });
    expect(data).not.toBeNull();
    expect(data.data).not.toBeUndefined();
    expect(data.visible).toBe(true)
    data.onCancel()
    expect(data.visible).toBe(true)
    data.onClose()
    expect(data.visible).toBe(false)
    data.onShow()
    expect(data.visible).toBe(true)
    expect(data.onCancel).not.toBeNull();
    expect(data.onOk).not.toBeNull();
  });
});
