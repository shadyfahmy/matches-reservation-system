import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminstratorPageComponent } from './adminstrator-page.component';

describe('AdminstratorPageComponent', () => {
  let component: AdminstratorPageComponent;
  let fixture: ComponentFixture<AdminstratorPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminstratorPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminstratorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
