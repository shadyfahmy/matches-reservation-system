import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EplToolbarComponent } from './epl-toolbar.component';

describe('EplToolbarComponent', () => {
  let component: EplToolbarComponent;
  let fixture: ComponentFixture<EplToolbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EplToolbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EplToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
